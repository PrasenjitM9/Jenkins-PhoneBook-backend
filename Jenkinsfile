pipeline {
  // "Top-level" agent is assigned to docker slaves via Jenkins pipeline configuration
  agent none

  stages {
    stage('Docker node test') {
      agent {
        docker {
          image 'node:7-alpine'
          args '--name docker-node' // list any args
        }
      }
      steps {
        // Steps run in node:7-alpine docker container on docker slave
        sh 'node --version'
      }
    }

    stage('Docker maven test') {
      agent {
        docker {
          image 'maven:3-alpine'
        }
      }
      steps {
        // Steps run in maven:3-alpine docker container on docker slave
        sh 'mvn --version'
      }
    }
  }
} 
