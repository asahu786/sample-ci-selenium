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
          bat 'docker compose up --build -d'
          bat 'ping -n 10 127.0.0.1 > nul' // Windows-safe sleep
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
