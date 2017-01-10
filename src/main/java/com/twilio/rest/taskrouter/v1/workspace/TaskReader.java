/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TaskReader extends Reader<Task> {
    private final String workspaceSid;
    private Integer priority;
    private Task.Status assignmentStatus;
    private String workflowSid;
    private String workflowName;
    private String taskQueueSid;
    private String taskQueueName;
    private String taskChannel;
    private String evaluateTaskAttributes;
    private String ordering;
    private Boolean hasAddons;

    /**
     * Construct a new TaskReader.
     * 
     * @param workspaceSid The workspace_sid
     */
    public TaskReader(final String workspaceSid) {
        this.workspaceSid = workspaceSid;
    }

    /**
     * The priority.
     * 
     * @param priority The priority
     * @return this
     */
    public TaskReader setPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * The assignment_status.
     * 
     * @param assignmentStatus The assignment_status
     * @return this
     */
    public TaskReader setAssignmentStatus(final Task.Status assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    /**
     * The workflow_sid.
     * 
     * @param workflowSid The workflow_sid
     * @return this
     */
    public TaskReader setWorkflowSid(final String workflowSid) {
        this.workflowSid = workflowSid;
        return this;
    }

    /**
     * The workflow_name.
     * 
     * @param workflowName The workflow_name
     * @return this
     */
    public TaskReader setWorkflowName(final String workflowName) {
        this.workflowName = workflowName;
        return this;
    }

    /**
     * The task_queue_sid.
     * 
     * @param taskQueueSid The task_queue_sid
     * @return this
     */
    public TaskReader setTaskQueueSid(final String taskQueueSid) {
        this.taskQueueSid = taskQueueSid;
        return this;
    }

    /**
     * The task_queue_name.
     * 
     * @param taskQueueName The task_queue_name
     * @return this
     */
    public TaskReader setTaskQueueName(final String taskQueueName) {
        this.taskQueueName = taskQueueName;
        return this;
    }

    /**
     * The task_channel.
     * 
     * @param taskChannel The task_channel
     * @return this
     */
    public TaskReader setTaskChannel(final String taskChannel) {
        this.taskChannel = taskChannel;
        return this;
    }

    /**
     * The evaluate_task_attributes.
     * 
     * @param evaluateTaskAttributes The evaluate_task_attributes
     * @return this
     */
    public TaskReader setEvaluateTaskAttributes(final String evaluateTaskAttributes) {
        this.evaluateTaskAttributes = evaluateTaskAttributes;
        return this;
    }

    /**
     * The ordering.
     * 
     * @param ordering The ordering
     * @return this
     */
    public TaskReader setOrdering(final String ordering) {
        this.ordering = ordering;
        return this;
    }

    /**
     * The has_addons.
     * 
     * @param hasAddons The has_addons
     * @return this
     */
    public TaskReader setHasAddons(final Boolean hasAddons) {
        this.hasAddons = hasAddons;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Task ResourceSet
     */
    @Override
    public ResourceSet<Task> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Task ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Task> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks",
            client.getRegion()
        );
        
        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Task> nextPage(final Page<Task> page, 
                               final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.TASKROUTER.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Task Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Task> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Task read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Page.fromJson(
            "tasks",
            response.getContent(),
            Task.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (priority != null) {
            request.addQueryParam("Priority", priority.toString());
        }
        
        if (assignmentStatus != null) {
            request.addQueryParam("AssignmentStatus", assignmentStatus.toString());
        }
        
        if (workflowSid != null) {
            request.addQueryParam("WorkflowSid", workflowSid);
        }
        
        if (workflowName != null) {
            request.addQueryParam("WorkflowName", workflowName);
        }
        
        if (taskQueueSid != null) {
            request.addQueryParam("TaskQueueSid", taskQueueSid);
        }
        
        if (taskQueueName != null) {
            request.addQueryParam("TaskQueueName", taskQueueName);
        }
        
        if (taskChannel != null) {
            request.addQueryParam("TaskChannel", taskChannel);
        }
        
        if (evaluateTaskAttributes != null) {
            request.addQueryParam("EvaluateTaskAttributes", evaluateTaskAttributes);
        }
        
        if (ordering != null) {
            request.addQueryParam("Ordering", ordering);
        }
        
        if (hasAddons != null) {
            request.addQueryParam("HasAddons", hasAddons.toString());
        }
        
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}