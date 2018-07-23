package cn.itcast.cxf.service;

import cn.itcast.cxf.domain.User;

import javax.ws.rs.*;
import java.util.List;

//@Path("/userService")
public interface IUserService {

	@POST
	@Path("/user")
	@Consumes({ "application/xml", "application/json" })
	public void saveUser(User user);

	@PUT
	@Path("/user")
	@Consumes({ "application/xml", "application/json" })
	public void updateUser(User user);

	@GET
	@Path("/user")
	@Produces({ "application/xml", "application/json" })
	public List<User> findAllUsers();

	@GET
	@Path("/user/{id}")
	@Consumes("application/xml")
	public User finUserById(@PathParam("id") Integer id);

	@DELETE
	@Path("/user/{id}")
	@Consumes("application/xml")
	public void deleteUser(@PathParam("id") Integer id);
}
