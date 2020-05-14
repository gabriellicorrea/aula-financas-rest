package financas.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import financas.model.Conta;
import financas.service.ContaService;

@Path("/contas")
public class ContaResource {
	private ContaService contas = new ContaService();
	
	@GET
	@Produces
	public Response getAll() {
		return Response.ok(contas).build();
	}
	
	@Path("/{numero}")
	@GET
	@Produces({"aplication/json"})
	public Response get(@PathParam("numero") Integer numero) {
		Conta _conta = contas.get(numero);
		if(_conta != null) {
			return Response.ok(_conta).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	@POST
	@Produces("aplication/json")
	@Consumes("aplication/json")
	public Response add(Conta conta) {
		contas.add(conta);
		return Response.ok(conta).build();
	}
		
	public Response delete(@PathParam("numero") Integer numero) {
		if (contas.delete(numero)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
