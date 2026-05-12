pipeline {
    agent any

    tools {
        maven 'MyMaven'
    }

    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: ['smoke', 'regression'],
            description: 'Select Test Suite'
        )
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
                bat "mvn test -Dgroups=${params.TEST_SUITE}"
            }
        }
        
        stage('Deploy Reports') {
    steps {
        bat 'xcopy target\\surefire-reports C:\\CD-Deploy\\Reports /E /I /Y'
    }
}