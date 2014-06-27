package org.springframework.cloud.service.common;

import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

import com.salesforce.de.dg.heroku.model.canvas.CanvasConsumer;

@ServiceLabel("canvasconsumer")
public class CanvasConsumerInfo extends BaseServiceInfo{

	private final CanvasConsumer canvasConsumer;
	
	public CanvasConsumerInfo(String id, CanvasConsumer canvasConsumer) {
		super(id);
		this.canvasConsumer = canvasConsumer;
	}

	public CanvasConsumer getCanvasConsumer() {
		return this.canvasConsumer;
	}
	
}
