package ua.com.fielden.personnel.structure;


public interface IQueue {
IQueue push(Pair<Pair<Object, Object>, Pair<Object, Object>> object);
Pair<Pair<Object, Object>, Pair<Object, Object>> pop();
Boolean isEmpty();
}
