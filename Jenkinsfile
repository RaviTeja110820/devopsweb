pipeline{
	agent{
        label 'linuxagent'
    }
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
                deploy adapters: [tomcat8(credentialsId: 'e653c22d-0123-48ef-b039-5c23279665f8', path: '', url: 'http://3.86.97.133:8090/')], contextPath: null, war: '**/*.war'
            }
        }
	}
}