pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    environment {
		DOCKERHUB_CREDENTIALS=credentials('27092013Malika')
	}
    stages {
        stage('Git connexion') {
            steps {
                // Get some code from a GitHub repository
                git branch:'Stock',url:'https://github.com/khlayel98/Devops-repo.git'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean"
                sh "mvn compile"
                sh "mvn package"
                // sh "mvn test"
            }
        }
        stage('test de qualité du code') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=27092013"
            }
        }
        stage('Junit et mockito test') {
            steps {
                sh 'mvn test'
                
            }
        }
        stage('Dépot du livrable') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
        stage('Build Docker image') {
            steps {sh 'echo image_exist'
                    sh 'docker build -t raniabenabdallah/achat .'
            }
            
        }
        stage('Dépot d image sur Docker hub') {
            steps {sh 'echo image_pushed'
               sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
               sh 'docker push raniabenabdallah/achat'
                }
        }
        stage('Run Docker-compose') {
            steps {
                   	sh 'docker-compose up -d'
            	}
        }
    }
    post {
       always {
          mail to: 'rania.benabdallah@esprit.tn',
             subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
             body: "${env.BUILD_URL} has result ${currentBuild.result}"
       }
     }
}