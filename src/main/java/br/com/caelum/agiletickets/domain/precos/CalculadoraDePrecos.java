package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	
	public static BigDecimal valorCalculadoDosBilhetes(Sessao sessao, double valor){
		
	return	sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(valor)));	
		
	}
	
	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo tipoDeEspetaculo = sessao.getEspetaculo().getTipo();
		
		double media =(sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue();
		
		
		if(tipoDeEspetaculo.equals(TipoDeEspetaculo.CINEMA) || tipoDeEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(media <= 0.05) { 
				preco= valorCalculadoDosBilhetes(sessao,0.10);
				
			} else {
				preco = sessao.getPreco();
			}
		} 
		
	else if(tipoDeEspetaculo.equals(TipoDeEspetaculo.BALLET) || tipoDeEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
		
			if(media <= 0.50) { 
					
				preco= valorCalculadoDosBilhetes(sessao,0.20);
				
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
			
		} 
	
	else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}