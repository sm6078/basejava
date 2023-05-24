/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int i = 0;
        while (storage[i] != null) {
            storage[i++] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
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
        int i = 0;
        boolean find = false;
        while (storage[i] != null && (i + 1 < storage.length)) {
            if (storage[i].uuid.equals(uuid)) {
                find = true;
            }
            if (find) {
                storage[i] = storage[i + 1];
            }
            i++;
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
        while (storage[i] != null) {
            i++;
        }
        return i;
    }
}
