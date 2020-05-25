package org.willishz.playground.grammar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Choose all java classes effectively prevent other classes from modifying their internal data.
 * Note that for the purposes of this question, neither reflection nor bytecode manipulation may be used to modify another class.
 */
public class FinalGetterOnly {

    static final class GetterOnly {
        protected final List<String> data;

        public GetterOnly(List<String> data) {
            this.data = data;
        }

        public List<String> getData() {
            return data;
        }

    }

    static class ProtectedFinal {
        protected final List<String> data;

        public ProtectedFinal(List<String> data) {
            this.data = data;
        }

        public List<String> getData() {
            return new ArrayList<>(data);
        }
    }

    static final class CopyFinal {
        private final List<String> data;

        public CopyFinal(List<String> data) {
            this.data = new ArrayList<>(data);
        }

        public List<String> getData() {
            return new ArrayList<>(data);
        }
    }

    static final class UnMod {
        private final List<String> data;

        public UnMod(List<String> data) {
            this.data = Collections.unmodifiableList(new ArrayList<>(data));
        }

        public List<String> getData() {
            return data;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        GetterOnly getterOnly = new GetterOnly(new ArrayList<String>() {{
            add("1");
        }});
        getterOnly.getData().add("2");
        System.out.println(getterOnly.getData().size()); // modify success

        ProtectedFinal protectedFinal = new ProtectedFinal(new ArrayList<String>() {{
            add("1");
        }});
        protectedFinal.getData().add("2"); // modify failed
        protectedFinal.data.add("2"); // modify success
        System.out.println(protectedFinal.getData().size());

        CopyFinal copyFinal = new CopyFinal(new ArrayList<String>() {{
            add("1");
        }});
        copyFinal.getData().add("2"); // modify failed
        copyFinal.data.add("2"); // modify successs
        System.out.println(copyFinal.getData().size());

        UnMod unMod = new UnMod(new ArrayList<String>() {{
            add("1");
        }});
//        unMod.getData().add("2"); // modify failed UnsupportedOperationException
//        unMod.data.add("2"); // modify failed UnsupportedOperationException
//        System.out.println(unMod.getData().size());
        UnMod unMod2 = new UnMod(new ArrayList<String>() {{
            add("1");
        }});
        Class<?> clazz = unMod2.getClass();
        Field field = clazz.getDeclaredField("data");
        field.setAccessible(true);
        field.set(unMod2, new ArrayList<String>() {{
            add("1");
            add("2");
        }});
        System.out.println(unMod2.getData().size()); // modify successs
    }
}
