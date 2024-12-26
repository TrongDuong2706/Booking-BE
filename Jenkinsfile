pipeline {
    agent {
        label 'ubuntu-server'
    }
    stages {

        stage('info') {
            steps {
                sh(script: """ pwd, whoami, ls -la """, label: "info steps")
            }
        }
    }
}
