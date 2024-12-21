pipeline {
    agent {
        label 'ubuntu-server'
    }
    environment {
        JAR_NAME = "identity-service-0.0.1-SNAPSHOT.jar"
        BUILD_SCRIPT = "mvn clean install -DskipTests=true"
        KILL_SCRIPT = "kill -9 \$(ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print \$2}')"
        RUN_USER = "trongduong"
        DEPLOY_FOLDER = "/home/trongduong/projects/deploy-be"
        COPY_SCRIPT = "sudo cp target/${JAR_NAME} ${DEPLOY_FOLDER}"
        RUN_SCRIPT = "nohup java -jar ${DEPLOY_FOLDER}/${JAR_NAME} > ${DEPLOY_FOLDER}/app.log 2>&1 &"
    }
    stages {
        stage('build') {
            steps {
                sh(script: """ ${BUILD_SCRIPT} """, label: "Build with Maven")
            }
        }
        stage('deploy') {
            steps {
                sh(script: """ ${COPY_SCRIPT} """, label: "Copy the .jar file into deploy folder")
                sh(script: """ ${KILL_SCRIPT} """, label: "Terminate the running process")
                sh(script: """ sudo su - ${RUN_USER} -c 'cd ${DEPLOY_FOLDER}; ${RUN_SCRIPT}' """, label: "Run the application as user")
            }
        }
    }
}
