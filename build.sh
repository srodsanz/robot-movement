#!/usr/bin/env bash

install_dir="$(realpath -m './ext/maven')"
mvn_version="3.9.5"
url="http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/${mvn_version}/binaries/apache-maven-${mvn_version}-bin.tar.gz"

echo "Running script $0"
echo "Beginning to build environment for project build"
read -p "Press any key to continue "

if [ ! -n "$(which java)" ]; then
  echo "Java not installed. Install it and check it is added to PATH"
  exit 1
fi

if [ -n "$(which mvn)" ]; then
  echo "Maven already installed on host machine."
else
  echo "Beginning to install maven on host ..."
  mkdir -p ${install_dir}
  curl -fsSL ${url} | tar -xz --strip-components=1 -C ${install_dir}
  export MAVEN_HOME=${install_dir}
  export M2_HOME=${install_dir}
  export PATH=${M2_HOME}/bin:$PATH
  echo "Maven installed in ${install_dir}. Checking version"
  mvn --version
fi

echo "Building ..."
mvn clean package
if [ $? -eq 0 ]; then
    echo "Build successful"
fi
