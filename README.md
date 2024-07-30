# robot-movement

This repository contains a basic implementation in JDK-21 of the
robot movement by the backtracking algorithmic scheme.

Compilation of this project is done via Maven by running the following command in bash terminal

```
$ mvn clean package
```
In the root directory of the project.

The executable is built without an associated version. From terminal the
main entrypoint of the app is run via
```
$ java -jar target/robot-movement-jar-with-dependencies.jar [-t][-h] <ENTRADA> <SALIDA>
```

From a bash-compatible terminal it is possible to fully compile the project by running
```
$ bash ./build.sh
```
This script already checks the installation of the main package managers for the JDK environment.
It is although necessary to install JDK and add its /bin directory to the PATH variable.
