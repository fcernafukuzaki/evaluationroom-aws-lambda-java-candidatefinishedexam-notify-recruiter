package pe.evaluationroom.candidatefinishedexam.notify.recruiter.bean;

public class Candidato extends Persona {

	private static final long serialVersionUID = 1L;

	private long idCandidato;
	
	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	
	public String getApellidoPaterno() {
		return super.getApellidoPaterno();
	}
	
	public String getApellidoMaterno() {
		return super.getApellidoMaterno();
	}
	
	public String getNombre() {
		return super.getNombre();
	}
	
	public String getCorreoElectronico() {
		return super.getCorreoElectronico();
	}

	@Override
	public String toString() {
		return "Candidato [idCandidato=" + idCandidato + ", getIdCandidato()=" + getIdCandidato()
				+ ", getApellidoPaterno()=" + getApellidoPaterno() + ", getApellidoMaterno()=" + getApellidoMaterno()
				+ ", getNombre()=" + getNombre() + ", getCorreoElectronico()=" + getCorreoElectronico() + "]";
	}
	
}
