package client.model.feature;

public class FactoryRaport {

    public static Raport getRaport(String criteria,String path)
    {
        if ( criteria.equals("pdf") )
            return new PdfMovieGenerate(path);
        else if ( criteria.equals("txt") )
            return new TxtMovieGenerate(path);
        return null;
    }
}
