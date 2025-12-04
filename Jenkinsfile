// Jenkinsfile (root)
pipeline {
  agent any
  options {
    skipDefaultCheckout(true)
    timestamps()
    ansiColor('xterm')
  }
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build App') {
      steps {
        dir('app') {
          sh 'mvn -B clean package'
        }
      }
    }
    stage('Compose Up') {
      steps {
        sh 'docker compose up --build -d'
        sh 'sleep 10' // give app + grid a moment to start
      }
    }
    stage('Run UI Tests') {
      steps {
        dir('tests') {
          sh 'mvn -B test'
        }
      }
    }
  }
  post {
    always {
      sh 'docker compose down || true'
      archiveArtifacts artifacts: 'app/target/*.jar, tests/target/surefire-reports/**', fingerprint: true
      junit 'tests/target/surefire-reports/*.xml'
    }
  }
}
