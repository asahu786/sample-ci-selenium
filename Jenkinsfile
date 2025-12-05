pipeline {
  agent any
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
          // Clean up any old containers
          bat 'docker compose down || exit 0'
          bat 'docker rm -f selenium-hub || exit 0'
          bat 'docker rm -f springboot-app || exit 0'
          bat 'docker rm -f chrome-node || exit 0'

          // Start fresh environment
          bat 'docker compose up --build -d'

          // Wait for app to start
          bat 'ping -n 20 127.0.0.1 > nul'
          bat 'curl -s http://localhost:8080/ || exit 1'
        }
      }
    }
    stage('Run UI Tests (TestNG)') {
      steps {
        ansiColor('xterm') {
          dir('tests') {
            bat 'mvn -B test -DsuiteXmlFile=testng.xml'
          }
        }
      }
    }
  }
  post {
    always {
      ansiColor('xterm') {
        bat 'docker compose down'
        archiveArtifacts artifacts: 'app/target/*.jar, tests/target/surefire-reports/**, tests/target/testng-results.xml', fingerprint: true
        junit allowEmptyResults: true, testResults: 'tests/target/surefire-reports/*.xml, tests/target/testng-results.xml'
      }
    }
  }
}
