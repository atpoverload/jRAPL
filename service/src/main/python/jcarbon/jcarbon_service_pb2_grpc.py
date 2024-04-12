# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import jcarbon.jcarbon_service_pb2 as jcarbon__service__pb2


class JCarbonServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.Start = channel.unary_unary(
                '/jcarbon.service.JCarbonService/Start',
                request_serializer=jcarbon__service__pb2.StartRequest.SerializeToString,
                response_deserializer=jcarbon__service__pb2.StartResponse.FromString,
                )
        self.Stop = channel.unary_unary(
                '/jcarbon.service.JCarbonService/Stop',
                request_serializer=jcarbon__service__pb2.StopRequest.SerializeToString,
                response_deserializer=jcarbon__service__pb2.StopResponse.FromString,
                )
        self.Dump = channel.unary_unary(
                '/jcarbon.service.JCarbonService/Dump',
                request_serializer=jcarbon__service__pb2.DumpRequest.SerializeToString,
                response_deserializer=jcarbon__service__pb2.DumpResponse.FromString,
                )


class JCarbonServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def Start(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def Stop(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def Dump(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_JCarbonServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'Start': grpc.unary_unary_rpc_method_handler(
                    servicer.Start,
                    request_deserializer=jcarbon__service__pb2.StartRequest.FromString,
                    response_serializer=jcarbon__service__pb2.StartResponse.SerializeToString,
            ),
            'Stop': grpc.unary_unary_rpc_method_handler(
                    servicer.Stop,
                    request_deserializer=jcarbon__service__pb2.StopRequest.FromString,
                    response_serializer=jcarbon__service__pb2.StopResponse.SerializeToString,
            ),
            'Dump': grpc.unary_unary_rpc_method_handler(
                    servicer.Dump,
                    request_deserializer=jcarbon__service__pb2.DumpRequest.FromString,
                    response_serializer=jcarbon__service__pb2.DumpResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'jcarbon.service.JCarbonService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class JCarbonService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def Start(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/jcarbon.service.JCarbonService/Start',
            jcarbon__service__pb2.StartRequest.SerializeToString,
            jcarbon__service__pb2.StartResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def Stop(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/jcarbon.service.JCarbonService/Stop',
            jcarbon__service__pb2.StopRequest.SerializeToString,
            jcarbon__service__pb2.StopResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def Dump(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/jcarbon.service.JCarbonService/Dump',
            jcarbon__service__pb2.DumpRequest.SerializeToString,
            jcarbon__service__pb2.DumpResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
