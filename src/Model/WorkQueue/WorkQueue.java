package Model.WorkQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a queue of work requests.
 * This could be associated with an Organization, Enterprise, or other entity
 * that processes tasks.
 */
public class WorkQueue {

    private List<WorkRequest> workRequestList;

    public WorkQueue() {
        this.workRequestList = new ArrayList<>();
    }

    public List<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }

    public void setWorkRequestList(List<WorkRequest> workRequestList) {
        this.workRequestList = workRequestList;
    }

    public void addWorkRequest(WorkRequest request) {
        this.workRequestList.add(request);
    }

    public void removeWorkRequest(WorkRequest request) {
        this.workRequestList.remove(request);
    }

    //  Methods to find or filter requests (e.g., by status, sender, etc.) can be added here
}