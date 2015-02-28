package org.jwt.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/echo")
public class EchoService {
	@GET
	@Path("/{message}")
	public String echo(@PathParam("message") String message) {
		log.debug(message);
		return message;
	}
	@GET
	public String echoWithPost(@QueryParam("message") String message) {
		log.debug(message);
		return message;
	}
}
