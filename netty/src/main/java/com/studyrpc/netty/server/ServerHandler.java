package com.studyrpc.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端消息处理
 * @author 20141022
 *
 */
public class ServerHandler extends SimpleChannelInboundHandler<String>{
	
	/**
	 * 注意：netty5方式
	 * Please keep in mind that #channelRead0(ChannelHandlerContext, I) will be renamed 
	 * to messageReceived(ChannelHandlerContext, I) in 5.0.
	 */
	/*@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {

		System.out.println(msg);
		
		ctx.channel().writeAndFlush("hi");
		ctx.writeAndFlush("hi");
	}*/

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
		ctx.channel().writeAndFlush("hi");
		//ctx.writeAndFlush("hi");//方式二
	}
	
	/**
	 * 新客户端接入
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive");
	}

	/**
	 * 客户端断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive");
	}

	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
	
}
