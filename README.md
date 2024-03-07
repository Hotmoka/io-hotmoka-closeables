[![Java-Build Action Status](https://github.com/Hotmoka/io-hotmoka-closeables/actions/workflows/java_build.yml/badge.svg)](https://github.com/Hotmoka/io-hotmoka-closeables/actions)
[![Maven Central](https://img.shields.io/maven-central/v/io.hotmoka.closeables/io-hotmoka-closeables-api.svg?label=Maven%20Central)](https://central.sonatype.com/search?smo=true&q=g:io.hotmoka.closeables)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

# Support classes for closeable objects in Java

This project defines classes that simplify the handling of closeable objects in Java.
In particular, it deals with two features: closure locks and on-close handlers.

## Closure locks

A closure lock is an object that, when locked, stops the creation of further method scopes
with an exception. It can be used to guarantee that, after a closeable object has been closed,
all outstanding calls to its methods can run to completion but new calls to its methods
lead to an exception.

The simplest use is to extend the `AbstractAutoCloseableWithLock` class as follows:

```java
public class MyCloseable extends AbstractAutoCloseableWithLock<MyException> {

  public MyCloseable() {
    super(MyException::new);
  }

  @Override
  public void close() {
    if (stopNewCalls())
      // close all resources here
  }

  public String getSomething() throws MyException {
    try (var scope = mkScope()) {
      return ...
    }
  }
}
```

After the object gets closed, any call to `getSomething()` will throw a `MyException`.
The call to `stopNewCalls()` allows outstanding calls to `getSomething` to terminate
before closing all resources. Moreover, `stopNewCalls()` return true only at its first call,
therefore the resources are closed only once.

## On-close handlers

On-close handlers can be registered (and unregistered)
on a closeable object, so that they get called when the object gets closed.

The simplest use is to extend the `AbstractAutoCloseableWithOnCloseHandlers` class as follows:

```java
public class MyCloseable extends AbstractAutoCloseableWithOnCloseHandlers {

  @Override
  public void close() {
    try {
      callCloseHandlers();
    }
    catch (Exception e) {
      // deal with any exception thrown by the registered close handlers
    }
  }
}
```

The `MyCloseable` class will inherit the `addOnCloseHandler` and `removeOnCloseHandler` methods.
Its `close()` method will fire all the registered close handlers through the call to
`callCloseHandlers()`. Moreover, if a close handler fails with an exception, all other close handlers
will be executed anyway, and only at the end the first exception will be thrown.

<p align="center"><img width="100" src="https://mirrors.creativecommons.org/presskit/buttons/88x31/png/by.png" alt="This documentation is licensed under a Creative Commons Attribution 4.0 International License"></p><p align="center">This document is licensed under a Creative Commons Attribution 4.0 International License.</p>

<p align="center">Copyright 2024 by Fausto Spoto (fausto.spoto@hotmoka.io)</p>