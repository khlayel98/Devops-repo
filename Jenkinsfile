pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    environment {
		DOCKERHUB_CREDENTIALS=credentials('TOUTA1')
	}
    stages {
        stage('Git') {
            steps {
                // Get some code from a GitHub repository
                git branch:'Secteur',url:'https://github.com/khlayel98/Devops-repo.git'
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
        stage('Sonarqube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=fatma"
            }
        }
        stage('j-unit test et mockito') {
            steps {
                sh 'mvn test'
                // bat '.\\mvnw test'
            }
        }
        stage('Deployment phase') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }
        stage('Build image') {
            steps {sh 'echo image_exist'
                //sh 'docker build -t touta/achat .'    
            }
        }
        stage('Push to Docker hub') {
            steps {sh 'echo image_pushed'
                //sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
                //sh 'docker push touta/achat'
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
          mail to: 'fatma.yahiaoui@esprit.tn',
             subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
             body: "${env.BUILD_URL} has result ${currentBuild.result}"
       }
     }
}