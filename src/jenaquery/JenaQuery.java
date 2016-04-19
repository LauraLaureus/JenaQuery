package jenaquery;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;

/**
 *
 * @author lala
 */
public class JenaQuery {

    public static void main(String[] args) {

        String ns = "urn:si2/ej3/";

        Model model = RDFDataMgr.loadModel("datos.ttl");

        String query = "PREFIX foaf:<http://xmlns.com/foaf/0.1/>\n"
                + "\n"
                + "SELECT ?nombre ?nombreamigo\n"
                + "WHERE {\n"
                + "   ?v foaf:name ?nombre .\n"
                + "   ?v foaf:knows ?amigo .\n"
                + "   ?amigo foaf:name ?nombreamigo .\n"
                + "}";
        
        Query executableQuery = QueryFactory.create(query);
        QueryExecution excecution = QueryExecutionFactory.create(executableQuery,model);
        
        ResultSet results = excecution.execSelect();
        for (; results.hasNext();) {
            QuerySolution sol = results.next();
            RDFNode x = sol.get("?nombre");
            System.out.println("--->" + PrintUtil.print(x));
        }
        
        excecution.close();
    }

}
