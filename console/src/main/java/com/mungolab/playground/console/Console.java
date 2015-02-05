package com.mungolab.playground.console;

import com.mungolab.playground.Client;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Vanja Komadinovic ( vanjakom@gmail.com )
 */
public class Console {
    public static void main(String[] args) {

        ClassLoader classLoaderV0 = null;
        ClassLoader classLoaderV0shade = null;
        ClassLoader classLoaderV1 = null;
        ClassLoader classLoaderV1shade = null;

        try {
            URL urlV0 = new URL(new URL("file:"), "./sample-client-v0/target/sample-client-v0-1.0-SNAPSHOT.jar");
            URL urlV1 = new URL(new URL("file:"), "./sample-client-v1/target/sample-client-v1-1.0-SNAPSHOT.jar");
            URL muturalDependency = new URL(new URL("file://"), "./mutural-dependency/target/mutural-dependency-1.0-SNAPSHOT.jar");

            URL urlV0shade = new URL(new URL("file:"), "./sample-client-v0/target/original-sample-client-v0-1.0-SNAPSHOT.jar");
            URL urlV1shade = new URL(new URL("file:"), "./sample-client-v1/target/original-sample-client-v1-1.0-SNAPSHOT.jar");

            classLoaderV0 = URLClassLoader.newInstance(new URL[] { urlV0, muturalDependency });
            classLoaderV1 = URLClassLoader.newInstance(new URL[] { urlV1, muturalDependency });

            classLoaderV0shade = URLClassLoader.newInstance(new URL[] { urlV0shade });
            classLoaderV1shade = URLClassLoader.newInstance(new URL[] { urlV1shade });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("ClassLoaders created");

        Client clientV0 = null;
        Client clientV1 = null;
        Client clientV0shade = null;
        Client clientV1shade = null;
        try {
            clientV0 = (Client) classLoaderV0.loadClass("com.mungolab.playground.impl.ClientImpl").newInstance();
            System.out.println("Class: " + clientV0.getClass());
            clientV1 = (Client) classLoaderV1.loadClass("com.mungolab.playground.impl.ClientImpl").newInstance();
            System.out.println("Class: " + clientV1.getClass());

            clientV0shade = (Client) classLoaderV0shade.loadClass("com.mungolab.playground.impl.ClientImpl").newInstance();
            System.out.println("Class: " + clientV0shade.getClass());
            clientV1shade = (Client) classLoaderV1shade.loadClass("com.mungolab.playground.impl.ClientImpl").newInstance();
            System.out.println("Class: " + clientV1shade.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Clients created");

        System.out.println("Client V0 version: " + clientV0.getVersion());
        System.out.println("Client V1 version: " + clientV1.getVersion());

        try {
            System.out.println("Client V0shade version: " + clientV0shade.getVersion());
            System.out.println("Client V1shade version: " + clientV1shade.getVersion());
        } catch (Throwable e) {
            System.out.println("Shade uber jars not working");
        }

        System.out.println("ClassLoaders created");
    }
}
