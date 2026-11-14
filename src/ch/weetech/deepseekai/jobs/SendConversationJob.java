package ch.weetech.deepseekai.jobs;

import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Creatable;

import ch.weetech.deepseekai.model.Conversation;
import ch.weetech.deepseekai.subscribers.DeekSeepHttpClientProvider;
import jakarta.inject.Inject;

@Creatable
public class SendConversationJob extends Job {
	
	@Inject
	private ILog loger;
	
	@Inject
	private DeekSeepHttpClientProvider clientProvider;
	
	@Inject
	private Conversation conversation;

	public SendConversationJob(String name) {
		super(DeepSeekJobConstants.JOB_PREFIX + " ask DeekSeek for help");
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		var deepseekClient = clientProvider.get();
		deekseekClient.setCancelProvider(() -> progressMonitor.isCanceled());
		
		try {
			var future = CompletableFuture.runAsync(deekseekClient.run(conversation))
					.thenApply(v -> Status.OK_STATUS)
					.exceptionally(e -> Status.error("Unable to run the task: " + e.getMessage(), e));
			return future.get();
		} catch (Exception e) {
			return Status.error(e.getMessage(), e);
		}
		
	}


}
