pipeline {
  agent any

  triggers {
    githubPush()
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

          // wait longer for app startup
          bat 'ping -n 40 127.0.0.1 > nul'
          // health check
          bat 'curl -s http://localhost:8089/actuator/health || exit 1'
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
       archiveArtifacts artifacts: 'app/target/*.jar, app/test-output/**', fingerprint: true
      publishTestNGResults testResultsPattern: 'app/test-output/testng-results.xml'
      }
    }
  }
}
