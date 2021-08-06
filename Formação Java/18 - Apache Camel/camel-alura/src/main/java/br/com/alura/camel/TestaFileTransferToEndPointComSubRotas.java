package br.com.alura.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class TestaFileTransferToEndPointComSubRotas {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			public void configure() throws Exception {
				from("file:pedidos?delay=5s&noop=true").
			    routeId("rota-pedidos").
			    to("seda:soap").
			    to("seda:http");

			from("seda:soap").
			routeId("rota-soap").
			to("xslt:pedido-para-soap.xslt").
			    log("Resultado do template: ${body}").
			    setHeader(Exchange.CONTENT_TYPE, constant("text/xml")).
			to("http4://localhost:8080/webservices/financeiro");

			from("seda:http").
			    routeId("rota-http").
			    setProperty("pedidoId", xpath("/pedido/id/text()")).
			    setProperty("email", xpath("/pedido/pagamento/email-titular/text()")).
			    split().
			        xpath("/pedido/itens/item").
			    filter().
			        xpath("/item/formato[text()='EBOOK']").
			    setProperty("ebookId", xpath("/item/livro/codigo/text()")).
			    setHeader(Exchange.HTTP_QUERY,
			            simple("clienteId=${property.email}&pedidoId=${property.pedidoId}&ebookId=${property.ebookId}")).
			to("http4://localhost:8080/webservices/ebook/item");
			}
		});

		context.start();
		Thread.sleep(20000);
		context.stop();

	}

}
