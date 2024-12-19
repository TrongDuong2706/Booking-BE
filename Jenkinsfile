pipeline{
    agent{
        label 'ubuntu-server'
    }
    stages{
        stage('info'){
            steps{
                sh(script:""" whoami;pwd;ls -la """, label: "first stage")
            }
        }
    }
}