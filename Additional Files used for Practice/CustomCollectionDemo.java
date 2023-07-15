import java.util.Iterator;

public class CustomCollectionDemo {
    public static void main(String[] args) {
        CustomTree<Contact> cc=new CustomTree<>();
        cc.add(new Contact("jfjsjf", "fjdfjsfksj", "jskj", "fjlskj"));
        cc.add(new Contact("jfjsjf", "fjdfj", "jskj", "fjlskj"));
        cc.add(new Contact("jfjsjf", "fjdfj", "jskj", "fjlskj"));
        cc.remove(new Contact("jfjsjf", "fjdsfjsjfj", "jskj", "fjlskj"));
        // cc.add("Apz");
        // cc.add("Anil");
        // cc.add("Charan");
        // cc.add("Azz");
        // cc.add("falakura");
        cc.inorder();
        System.out.println(cc.size());
        // cc.remove("Apz");
        // cc.remove("Mahesh");
        // cc.remove("Anil");
        // cc.remove("Charan");
        // cc.remove("falakura");
        // cc.remove("Azz");
        // cc.inorder();
        // System.out.println(cc.size());
        // for(String s:cc)
        // {
        //     System.out.println(s);
        // }
        Iterator<Contact> itr = cc.iterator();
        while(itr.hasNext())
        {
            System.out.println(itr.next().Name);
        }
        for(Contact c:cc)
        {
            System.out.println(c.Name);
        }
    }
}
