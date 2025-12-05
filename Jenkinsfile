pipeline {
  agent any
  tools{
  maven 'Maven_3'}
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
          bat 'sleep 10' // give app + grid a moment to start
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
        bat 'docker compose down || true'
        archiveArtifacts artifacts: 'app/target/*.jar, tests/target/surefire-reports/**, tests/target/testng-results.xml', fingerprint: true
        junit 'tests/target/surefire-reports/*.xml'
      }
    }
  }
}
