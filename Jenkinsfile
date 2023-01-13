pipeline{
	agent any
	tools{
		maven 'local_maven'
	}
	stages {
		stage('Build'){
			steps{
				sh 'mvn clean package'
			}
			post{
				success{
					echo "Archiving the Artifacts"
					archiveArtifacts artifacts: '**/target/*.war'
				}
			}

		}

		stage('Deploy to tomcat server'){
            steps{
				deploy adapters: [tomcat8(credentialsId: '338a88f8-d2e1-44a2-ab4d-9401c2b2ccec', path: '', url: 'http://18.207.116.133:8090/')], contextPath: null, war: '**/*.war'
			}
        }
	}
}