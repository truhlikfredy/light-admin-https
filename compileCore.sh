#!/bin/bash

cd lightadmin-core
mvn clean install

cp ./target/lightadmin-1.2.0.BUILD-SNAPSHOT.jar ../lightadmin-core-build/lightadmin-1.2.0.BUILD-SNAPSHOT.jar
