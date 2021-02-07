#!groovy

pipeline {
  agent none
  tools {
    //maven 'Maven 3.6.3'
    maven "Maven_Home"
    docker "Docker_Home"
  }
    //
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.5.0'
        }
      }
      steps {
        bat 'mvn clean install'
      }
    }
    stage('Docker Build') {
      agent any
      steps {
        bat 'docker build -t jeetdeveloper/spring-petclinic:latest .'
      }
    }
    stage('Docker Push') {
      agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'jeetdocker', passwordVariable: 'Jeetdeveloper@18', usernameVariable: 'jeetdeveloper')]) {
          bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          bat 'docker push jeetdeveloper/spring-petclinic:latest'
        }
      }
    }
  }
}
