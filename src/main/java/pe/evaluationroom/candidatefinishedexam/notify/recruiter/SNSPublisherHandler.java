package pe.evaluationroom.candidatefinishedexam.notify.recruiter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import pe.evaluationroom.candidatefinishedexam.notify.recruiter.bean.Candidato;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class SNSPublisherHandler implements RequestHandler<Candidato, APIGatewayProxyResponseEvent> {

	static final Logger logger = LogManager.getLogger(SNSPublisherHandler.class);
	
	private static final String TOPIC_ARN_EMAIL = "arn:aws:sns:us-east-1:305121837514:EvaluationRoom_CandidateFinishedExam-Notify-Recruiter";
	
	private static AmazonSNSClient snsClient = null;
	
	private String getEmailRecruiterSubject(String name) {
		return (new StringBuffer("El candidato ")).
				append(name).
				append(" ha finalizado el examen").
				append(new String("\ud83d\ude0a")).
				toString();
	}
	
	private String getEmailRecruiterMessage(String name) {
		return (new StringBuffer("El candidato ")).
				append(name).
				append(" ha finalizado el examen.").
				append("\n\n").
				append("Este es un mensaje automatizado del sistema EvaluationRoom. Por favor no responda al mismo.\r\n").
				append("Si necesita ponerse en contacto diríjase a: fcernaf@gmail.com").
				toString();
	}
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(Candidato input, Context context) {
		logger.info("[INICIO]" + input);
		
		// crear cliente SNS
		snsClient = (AmazonSNSClient) AmazonSNSClientBuilder.standard().build();
		snsClient.publish(TOPIC_ARN_EMAIL, 
				getEmailRecruiterMessage(input.getNombreCompleto()), 
				getEmailRecruiterSubject(input.getNombreCompleto()));
		
		String output = "Sent.";
		
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setStatusCode(200);
		responseEvent.setBody(output);
		return responseEvent;
	}
	
}
