package comp26120;

public class llist1 implements PriorityQueue {
    String value;
    int priority;
    llist1 next = null;
    llist1 prev = null;

    public llist1() {
	priority = 0;
    }

    public llist1(String value, int priority) {
        // A node to be inserted into a list
        this.value = value;
        this.priority = priority;
    }


    public boolean contains(String value, int priority) {
	// Linear search
	// Skip dummy
        llist1 tmp_next = next;
        while (tmp_next != null) {
            if (tmp_next.value.equals(value) && tmp_next.priority == priority) {
                return true;
            }
            tmp_next  = tmp_next.next;
        }

        return false;

    }

    public void insert(String value, int priority) {
	llist1 node = new llist1(value, priority);
	// insert after dummy
	node.next = this.next;
	if (this.next != null) {
            this.next.prev=node;
        }
        node.prev = this;
        this.next = node;
    }

    public boolean is_empty() {
        // Assumes single dummy
        return (this.next == null);
    }

    // This is why we need the dummy - we need the original pointer to still
    // be the pointer to the list even after we remove the minimum element
    public String pop_min() {
        if (this.next != null) {
            llist1 best_node = this.next;
            while (best_node.next != null) {
		best_node = best_node.next;
            }

            String value = best_node.value;

            // Remove best_node
            if (best_node.prev != null) {
                best_node.prev.next = best_node.next;
            }
            if (best_node.next != null) {
                best_node.next.prev = best_node.prev;
            }

            return value;
        }
        return null;
    }

    public void print() {
        System.out.format("<%s,%d> followed by...\n",value,priority);
        if (this.next != null) {
            next.print();
        } else {
            System.out.println("NULL");
        }
    }
}