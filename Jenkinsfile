pipeline {
    agent any
    stages {
        stage('Clone repository') {
            steps {	  
            checkout scm
            }
        }
       
        stage('Test API Rest'){
		steps{
            		sh 'newman run /home/ubuntu/apis_test/virtualTradeshow.postman_collection.json -e /home/ubuntu/apis_test/env1.postman_environment.json'
		}
	}

        stage('Deploy') {
            steps{                
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(configName: 'TestServer', 
                        transfers: 
                        [
                            sshTransfer(cleanRemote: false, 
                            excludes: '', 
                            execCommand: "cd /var/www/html/SplitMerge && sudo git checkout master && sudo git pull https://github.com/ifhams98/SplitMerge.git master && sudo git fetch https://github.com/ifhams98/SplitMerge.git master &&  sudo -H -u root bash -c 'pm2 restart 0'"
                            ,
                            execTimeout: 12000000, 
                            flatten: false, 
                            makeEmptyDirs: false, 
                            noDefaultExcludes: false, 
                            patternSeparator: '[, ]+', 
                            remoteDirectory: '', 
                            remoteDirectorySDF: false, 
                            removePrefix: '', 
                            sourceFiles: '')
                        ], 
                        usePromotionTimestamp: false, 
                        useWorkspaceInPromotion: false, 
                        verbose: false)
                    ]
                )
            }
            
        }
    
    }
    post{
		success {
			emailext (
			subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
        	body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p><p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            to: '$DEFAULT_RECIPIENTS',
			recipientProviders: [[$class: 'DevelopersRecipientProvider']]
      	  	)
    	}
		failure {
      		emailext (
          	subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          	body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p><p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            to: '$DEFAULT_RECIPIENTS',
          	recipientProviders: [[$class: 'DevelopersRecipientProvider']]
        	)
    		}
	}
}
