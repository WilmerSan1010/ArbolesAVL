package arbolesavl;

import java.util.Scanner;

// Clase para representar un nodo en el árbol AVL
class Nodo {

    int raiz;
    int altura;
    Nodo izquierda, derecha;

    Nodo(int d) {
        raiz = d;
        altura = 1;
    }
}

    class AVLTree {

    Nodo root;

   
    int getAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Función para obtener el máximo de dos números enteros
    int max(int a, int b) {
        return Math.max(a, b);
    }

    // Función para realizar una rotación hacia la derecha
    Nodo derechaRotacion(Nodo y) {
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        // Realizar la rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar las alturas
        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;

        // Devolver el nuevo nodo raíz
        return x;
    }

    // Función para realizar una rotación hacia la izquierda
    Nodo izquierdaRotacion(Nodo x) {
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        // Realizar la rotación
        y.izquierda = x;
        x.derecha = T2;

        // Actualizar las alturas
        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;

        // Devolver el nuevo nodo raíz
        return y;
    }

    // Función para obtener el factor de equilibrio de un nodo
    int getBalance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }

    // Función para insertar un nodo en el árbol AVL
    Nodo insertNodo(Nodo nodo, int llave) {
        // Realizar la inserción en un BST normal
        if (nodo == null) {
            return (new Nodo(llave));
        }

        if (llave < nodo.raiz) {
            nodo.izquierda = insertNodo(nodo.izquierda, llave);
        } else if (llave > nodo.raiz) {
            nodo.derecha = insertNodo(nodo.derecha, llave);
        } else {
            return nodo; // No se permiten valores duplicados
        }
        // Actualizar la altura del nodo actual
        nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));

        // Obtener el factor de equilibrio del nodo actual
        int balance = getBalance(nodo);

        // Caso de rotación izquierda-izquierda
        if (balance > 1 && llave < nodo.izquierda.raiz) {
            return derechaRotacion(nodo);
        }

        // Caso de rotación derecha-derecha
        if (balance < -1 && llave > nodo.derecha.raiz) {
            return izquierdaRotacion(nodo);
        }

        // Caso de rotación izquierda-derecha
        if (balance > 1 && llave > nodo.izquierda.raiz) {
            nodo.izquierda = izquierdaRotacion(nodo.izquierda);
            return derechaRotacion(nodo);
        }

        // Caso de rotación derecha-izquierda
        if (balance < -1 && llave < nodo.derecha.raiz) {
            nodo.derecha = derechaRotacion(nodo.derecha);
            return izquierdaRotacion(nodo);
        }

        // El árbol está balanceado de forma correcta
        return nodo;
    }

    // Función para recorrer en orden el árbol AVL
    void inorderTraversal(Nodo nodo) {
        if (nodo != null) {
            inorderTraversal(nodo.izquierda);
            System.out.print(nodo.raiz + " ");
            inorderTraversal(nodo.derecha);
        }
    }

    // Función para recorrer en preorden el árbol AVL
    void preorderTraversal(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.raiz + " ");
            preorderTraversal(nodo.izquierda);
            preorderTraversal(nodo.derecha);
        }
    }

    // Función para recorrer en posorden el árbol AVL
    void postorderTraversal(Nodo nodo) {
        if (nodo != null) {
            postorderTraversal(nodo.izquierda);
            postorderTraversal(nodo.derecha);
            System.out.print(nodo.raiz + " ");
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los nodos (ingrese -1 para detener la entrada):");
        int nodevalor;

        while ((nodevalor = scanner.nextInt()) != -1) {
            tree.root = tree.insertNodo(tree.root, nodevalor);
        }

        System.out.println("Arbol AVL en orden:");
        tree.inorderTraversal(tree.root);

        System.out.println("\nArbol AVL en preorden:");
        tree.preorderTraversal(tree.root);

        System.out.println("\nArbol AVL en posorden:");
        tree.postorderTraversal(tree.root);
        
        //modificando
    }
}
