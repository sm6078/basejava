/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int i = 0;
        int currentSize = size();
        while (i < currentSize && storage[i] != null) {
            storage[i++] = null;
        }
    }

    void save(Resume r) {
        int currentSize = size();
        int index = currentSize > 0 ? currentSize : 0;
        storage[index] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int currentSize = size();
        boolean change = false;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                change = true;
            }
            if (change && i < currentSize - 1) {
                storage[i] = storage[i + 1];
            }
        }
        if (change) {
            storage[currentSize - 1] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumeTwo = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            resumeTwo[i] = storage[i];
        }
        return resumeTwo;
    }

    int size() {
        int i = 0;
        while (i < storage.length && storage[i] != null) {
            i++;
        }
        return i;
    }
}
