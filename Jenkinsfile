pipeline {
  agent any

  // Add triggers here
  triggers {
    githubPush()   // or gitlabPush(), depending on plugin installed
  }

  tools {
    maven 'Maven_3'
  }

  options {
    skipDefaultCheckout(true)
    timestamps()
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build App') {
      steps {
        ansiColor('xterm') {
          dir('app') {
            bat 'mvn -version'
            bat 'mvn -B clean package'
          }
        }
      }
    }

    stage('Compose Up') {
      steps {
        ansiColor('xterm') {
          bat 'docker compose down || exit 0'
          bat 'docker rm -f selenium-hub || exit 0'
          bat 'docker rm -f springboot-app || exit 0'
          bat 'docker rm -f selenium-chrome || exit 0'
          bat 'docker network prune -f || exit 0'

          bat 'docker compose up --build -d'

          bat 'ping -n 20 127.0.0.1 > nul'
          bat 'curl -s http://localhost:8089/ || exit 1'
        }
      }
    }

    stage('Run UI Tests (TestNG)') {
      steps {
        ansiColor('xterm') {
          dir('app') {
            bat 'mvn -B test'
          }
        }
      }
    }
  }

  post {
    always {
      ansiColor('xterm') {
        bat 'docker compose down'
        archiveArtifacts artifacts: 'app/target/*.jar, app/target/surefire-reports/**, app/target/testng-results.xml', fingerprint: true
        junit allowEmptyResults: true, testResults: 'app/target/surefire-reports/*.xml, app/target/testng-results.xml'
      }
    }
  }
}