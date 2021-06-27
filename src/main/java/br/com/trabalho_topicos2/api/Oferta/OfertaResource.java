package br.com.trabalho_topicos2.api.Oferta;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ofertas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class OfertaResource {
    
    @PersistenceContext(unitName = "TrabalhoTopicosPU")
    private EntityManager entityManager;

    public OfertaResource() {
    }
    
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") int id) {
        Oferta oferta = findOferta(id);        
        if (oferta == null) {
            return ofertaNotFoundResponse();
        }
        return Response.ok(oferta).build();
    }
    
    @GET
    public List<Oferta> getAll(@QueryParam("empresaID") int idEmpresa, @QueryParam("destaque") boolean destaque) {
        if (idEmpresa > 0) {
            return entityManager
                    .createQuery("SELECT a FROM Oferta a WHERE a.empresa.id = :idEmpresa", Oferta.class)
                    .setParameter("idEmpresa", idEmpresa)
                    .getResultList();
        }
        
        if (destaque) {
            return entityManager
                    .createQuery("SELECT a FROM Oferta a WHERE a.destaque = :destaque", Oferta.class)
                    .setParameter("destaque", destaque)
                    .getResultList();
        }
        return entityManager.createQuery("SELECT a FROM Oferta a", Oferta.class).getResultList();
    }
    
//    @GET
//    public List<Oferta> getAll() {
//        return entityManager.createQuery("SELECT a FROM Oferta a", Oferta.class).getResultList();
//    }
//    
//    @GET
//    public List<Oferta> getByEmpresa(@QueryParam("empresaID") int id) {
//        return entityManager.createQuery("SELECT a FROM Oferta a WHERE a.empresa.id = ", Oferta.class).getResultList();
//    }

    @POST
    public Response addOferta(Oferta oferta) {
        entityManager.persist(oferta);
        return Response.status(Response.Status.CREATED).entity(oferta).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Oferta oferta = findOferta(id);
        if(oferta == null) {
            return ofertaNotFoundResponse();
        } 
        entityManager.remove(oferta);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Oferta ofertaAtualizado) {
        Oferta oferta = findOferta(id);
        if(oferta == null) {
            return ofertaNotFoundResponse();
        }
        ofertaAtualizado.setId(id);
        entityManager.merge(ofertaAtualizado);
        return Response.ok(oferta).build();
    }
    
    public Oferta findOferta(int id) {
        return entityManager.find(Oferta.class, id);
    }
    
    public Response ofertaNotFoundResponse() {      
        return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Oferta não encontrada!")
                    .build();
    }
}
