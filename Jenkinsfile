pipeline {
    agent any

    tools {
        maven 'MyMaven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'developer-branch',
                url: 'https://github.com/SANKET1999-10/PetStoreAutomation.git'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn test -Dgroups=smoke'
            }
        }
    }
}