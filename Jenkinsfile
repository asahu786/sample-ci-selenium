pipeline {
  agent any
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
            sh 'mvn -version'
            sh 'mvn -B clean package'
          }
        }
      }
    }
    stage('Compose Up') {
      steps {
        ansiColor('xterm') {
          sh 'docker compose up --build -d'
          sh 'sleep 10' // give app + grid a moment to start
        }
      }
    }
    stage('Run UI Tests (TestNG)') {
      steps {
        ansiColor('xterm') {
          dir('tests') {
            sh 'mvn -B test -DsuiteXmlFile=testng.xml'
          }
        }
      }
    }
  }
  post {
    always {
      ansiColor('xterm') {
        sh 'docker compose down || true'
        archiveArtifacts artifacts: 'app/target/*.jar, tests/target/surefire-reports/**, tests/target/testng-results.xml', fingerprint: true
        junit 'tests/target/surefire-reports/*.xml'
      }
    }
  }
}
