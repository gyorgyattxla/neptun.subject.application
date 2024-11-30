pipeline{
    agent any
    stages{
        stage('Check Maven version'){
            steps{
                sh '-maven version'
            }
        }
        stage('Package'){
            steps{
                sh 'mvm package'
                archiveArtifacts artifacts: '**/target/*.war'
            }
        }
    }
    post{
        always{
            junit '**/target/surefire-reports/junitreports/*'
        }
    }
}