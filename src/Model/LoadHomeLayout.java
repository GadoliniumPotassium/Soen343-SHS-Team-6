package Model;

public class LoadHomeLayout {
	public String[][] readFile(String filename){
        BufferedReader br=new BufferedReader(new FileReader(filename));
        ArrayList <String> lines=new ArrayList<String>();
        String line=null;
        while((line=br.readLine())!=null){
        lines.add(line);
        }
        br.close();
        String information [][]=new String[lines.size()][5];
        for (int i=0;i<information.length;i++){
            String y=lines.get(i);
            String z []=y.split(";");
            for(int j=0;j<information[i].length;j++){
                information[i][j]=z[j];
            }
        }
        return information;
    }
}
