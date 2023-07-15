import java.util.Iterator;

class Node<T extends Comparable<T>>
{
    T data;
    Node<T> left;
    Node<T> right;

    Node()
    {}
    
    Node(T data)
    {
        this.data = data;
        this.left = this.right = null;
    }

    public String toString()
    {
        return this.data.toString();
    }

    // public int compareTo(T data)
    // {
    //     return this.data.compareTo(data);
    // }
}
public class CustomTree<T extends Comparable<T>> implements Iterable<T>{
    Node<T> root;
    private int length;

    CustomTree()
    {
        this.root = null;
        length = 0;
    }

    public void add(T data)
    {
        Node<T> node = new Node<>();
        node.data = data;
        if(root == null)
        {
            root = node;
            length++;
            return;
        }
        Node<T> p = new Node<>(),q = root;
        while(q != null)
        {
            p = q;
            if(node.data.compareTo(q.data) == 0)
            {
                q = node;
                return;
            }
            if(node.data.compareTo(q.data) < 0)
            {
                q = q.left;
            }
            else if(node.data.compareTo(q.data) > 0)
            {
                q = q.right;
            }
        }
        if(node.data.compareTo(p.data) < 0)
        {
            p.left = node;
            length++;
        }
        else
        {
            p.right = node;
            length++;
        }
    }

    public void remove(T x)
    {
        this.root = remove(this.root,x);
    }

    public Node<T> remove(Node<T> node,T x)
    {
        if(node == null)
        {
            return null;
        }
        else if(x.compareTo(node.data) < 0)
        {
            node.left = remove(node.left,x);
        }
        else if(x.compareTo(node.data) > 0)
        {
            node.right = remove(node.right,x);
        }
        else
        {
            if(node.left == null || node.right == null)
            {
                if(node.right == null)
                {
                    node = node.left;
                    length--;
                }
                else if(node.left == null)
                {
                    node = node.right;
                    length--;
                }
            }
            else
            {
                Node<T> p = node.right;
                while(p.left != null)
                {
                    p = p.left;
                }
                node.data = p.data;
                node.right = remove(node.right,p.data);
            }
        }
        return node;
    }

    public void inorder()
    {
        inOrder(this.root);
    }

    public void inOrder(Node<T> p)
    {
        try
        {
        if(p.data == null)
            return;
        if(p.left != null)
        inOrder(p.left);
        System.out.println(p.data);
        if(p.right != null)
        inOrder(p.right);
        }catch(Exception e){}
    }

    public int size()
    {
        return this.length;
    }

    public Iterator<T> iterator()
    {
        return new CustomIterator<>(root);
    }
}
class CustomIterator<T extends Comparable<T>> implements Iterator<T>
{
    CustomLinkedList<T> ll;
    T curr;

    CustomIterator(Node<T> root)
    {
        ll = new CustomLinkedList<>();
        fill(root);
    }

    public boolean hasNext()
    {
        return !ll.isEmpty();
    }

    public T next()
    { 
        curr = ll.remove();
        return curr;
    }
    
    public void fill(Node<T> root)
    {
        try{
        if(root == null)
            return;
        if(root.left != null)
            fill(root.left);
        ll.add(root.data);
        if(root.right != null)
            fill(root.right);
        }
        catch(Exception e){}
    }
}
