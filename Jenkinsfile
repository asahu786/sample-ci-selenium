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
          // Clean up any old containers before starting
          bat 'docker compose down || exit 0'
          bat 'docker rm -f selenium-hub || exit 0'
          // Bring up fresh environment
          bat 'docker compose up --build -d'
          // Windows-safe sleep to give app time to start
          bat 'ping -n 15 127.0.0.1 > nul'
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
        // Always tear down containers after run
        bat 'docker compose down'
        archiveArtifacts artifacts: 'app/target/*.jar, tests/target/surefire-reports/**, tests/target/testng-results.xml', fingerprint: true
        junit allowEmptyResults: true, testResults: 'tests/target/surefire-reports/*.xml, tests/target/testng-results.xml'
      }
    }
  }
}
