package br.com.trabalho_topicos2.api.categoria;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class CategoriaResource {
    
    @PersistenceContext(unitName = "TrabalhoTopicosPU")
    private EntityManager entityManager;

    public CategoriaResource() {
    }
    
    @GET
    @Path("{id}")
    public Response getCategoriaById(@PathParam("id") int id) {
        Categoria categoria = findCategoria(id);        
        if (categoria == null) {
            return categoriaNotFoundResponse();
        }
        return Response.ok(categoria).build();
    }
    
    @GET
    public List<Categoria> getAll() {
        return entityManager.createQuery("SELECT a FROM Categoria a", Categoria.class).getResultList();
    }

    @POST
    public Response addCategoria(Categoria categoria) {
        entityManager.persist(categoria);
        return Response.status(Response.Status.CREATED).entity(categoria).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Categoria categoria = findCategoria(id);
        if(categoria == null) {
            return categoriaNotFoundResponse();
        } 
        entityManager.remove(categoria);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Categoria categoriaAtualizado) {
        Categoria categoria = findCategoria(id);
        if(categoria == null) {
            return categoriaNotFoundResponse();
        }
        categoriaAtualizado.setId(id);
        entityManager.merge(categoriaAtualizado);
        return Response.ok(categoria).build();
    }
    
    public Categoria findCategoria(int id) {
        return entityManager.find(Categoria.class, id);
    }
    
    public Response categoriaNotFoundResponse() {      
        return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Categoria não encontrada!")
                    .build();
    }
}
