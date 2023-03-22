pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
      
        stage('Build') {
            steps {
                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }            
        }
      
      stage('Test') {
            steps {
                // To run Maven on a Windows agent, use
                bat "mvn test"
            }            
        }
      
       stage('Install') {
            steps {
                // To run Maven on a Windows agent, use
                bat "mvn install"
            }            
        }
    }
}
