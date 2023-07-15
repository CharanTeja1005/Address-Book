class Node1<T>
{
    T data;
    Node1<T> next;
    
    Node1(T data)
    {
        this.data = data;
        next = null;
    }
}

public class CustomLinkedList<T> {
    Node1<T> head;
    private int length;

    CustomLinkedList()
    {
        this.head = null;
        length = 0;
    }

    public void add(T data)
    {
        Node1<T> node = new Node1<>(data);
        length++;
        if(head == null)
        {
            head = node;
            return;
        }
        Node1<T> temp = head;
        while(temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = node;
    }

    public T remove()
    {
        if(head == null)
        {
            return null;
        }
        Node1<T> temp = head;
        head = head.next;
        length--;
        return temp.data;
    }

    public boolean isEmpty()
    {
        return length == 0 ? true : false;
    }
}
